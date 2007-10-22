package util;

/**
 * This class offers the functionality for the k-means non-hierarchical
 * clustering algorithm. Non-hierarchical clustering algorithms are
 * characterised by an initial segregation of the objects in K clusters, which
 * are improved iteratively.
 * 
 * The k-means algorithm works as follows : 1) divide the objects in K groups
 * and calculate the mean value of each cluster. 2) for every object : calculate
 * the distance to the mean value of every cluster and assign the object to the
 * cluster with the nearest mean. If an object is assigned to another cluster,
 * the means of the 2 should be recalculated. 3) repeat step 2 until no
 * reallocation is necessary.
 * 
 * In order to improve the performance of the algorithm, the initialization
 * phase (1) sorts the row of objects that will be clustered, before dividing
 * them into K clusters, each containing the same number of objects.
 * 
 * Standard this implementation supports only a 1-dimensional clustering
 * problem, since in the current implementation the distance between 2 objects
 * (ints) a and b is defined as (|a-b|), but it can easily be extended by
 * implementing a different distance function (e.g. a euclidean of Mahalanobis
 * distance function).
 * 
 * 
 * @author <a href="mailto:Bart.Vandervelde@synes.com">Bart Vandervelde</a>
 */

public class Kmeans_old {
    private Cluster[] mClusters;

    private int mK;

    private short[] mData;

    private boolean mAllocationNeeded;

    class Cluster {
        public IntVector mData;

        public IntVector mCounts;

        public int mSum;

        public int mSize;

        public Cluster() {
            this.mData = new IntVector();
            this.mCounts = new IntVector();
            this.mSum = 0;
            this.mSize = 0;
        }

        public Cluster(int aCapacity) {
            this.mData = new IntVector(aCapacity);
            this.mCounts = new IntVector(aCapacity);
            this.mSum = 0;
            this.mSize = 0;
        }

        public Cluster(int aValue, int aCount) {
            this.mSum = 0;
            this.mSize = 0;
            this.mData = new IntVector(1);
            this.mCounts = new IntVector(1);
            add(aValue, aCount);
        }

        public void add(int aValue) {
            add(aValue, 1);
        }

        public void add(int aValue, int aCount) {
            int index = getIndex(aValue);
            if (index == -1) {
                this.mData.addElement(aValue);
                this.mCounts.addElement(aCount);
            } else if (this.mData.get(index) != aValue) {
                this.mData.insertElementAt(aValue, index);
                this.mCounts.insertElementAt(aCount, index);
            } else {
                int count = this.mCounts.get(index) + aCount;
                this.mCounts.removeElementAt(index);
                this.mCounts.insertElementAt(count, index);
            }

            this.mSum += (aValue * aCount);
            this.mSize += aCount;
        }

        public void remove(int aIndex) {
            int count = getCount(aIndex);
            int value = getValue(aIndex);
            this.mData.removeElementAt(aIndex);
            this.mCounts.removeElementAt(aIndex);
            this.mSum -= (value * count);
            this.mSize -= count;
        }

        public int getValue(int aIndex) {
            return this.mData.get(aIndex);
        }

        public int getCount(int aIndex) {
            return this.mCounts.get(aIndex);
        }

        public int getMean() {
            return (int) ((int) this.mSum) / ((int) this.mSize);
        }

        public int getSum() {
            return this.mSum;
        }

        public int getSize() {
            return this.mSize;
        }

        public int getDistinct() {
            return this.mData.size();
        }

        public int getMaximum() {
            return this.mData.get(this.mData.size() - 1);
        }

        public int[] getData() {
            return this.mData.toArray();
        }

        private int getIndex(int aValue) {
            for (int i = 0; i != this.mData.size(); i++) {
                if (this.mData.get(i) >= aValue)
                    return i;
            }

            return -1;
        }
    }

    /**
     * Constructor for kMeans.
     * 
     * @param aData
     *            data to be clustered in k groups
     * @param aK
     *            indicating in how many clusters data need to be divided
     */
    public Kmeans_old(short[] aData, int aK) {
        if (aData == null)
            throw new NullPointerException("Data may not be null");
        if (aK == 0)
            throw new IllegalArgumentException("k may not be 0");
        if (aData.length == 0)
            throw new IllegalArgumentException(
                    "data must contain at least one record");

        this.mK = aK;
        this.mData = aData;
        init();
    }

    /**
     * Processing method : it combines all the sub steps in the algorithm (1-3)
     * 
     */
    public void process() {
        if (this.mAllocationNeeded)
            while (allocate() != 0)
                ;
    }

    /**
     * @deprecated use process instead of fastProcess.
     * 
     */
    public void fastProcess() {
        process();
    }

    /**
     * Returns the means of the clusters.
     * 
     * @return array of ints, each of them representing the center of a cluster
     */
    public int[] getMeans() {
        int i = 0, s = this.mClusters.length;
        int[] means = new int[s];
        for (; i != s; i++)
            means[i] = this.mClusters[i].getMean();

        return means;
    }

    /**
     * Returns the borders between the clusters. (In reality these are the
     * maxima of each cluster, with exception of the last one).
     * 
     * @return array of ints, each of them representing a border between two
     *         consecutive clusters
     */
    public int[] getBorders() {
        int i = 0, s = this.mClusters.length;
        int[] borders = new int[s - 1];
        for (; i != s - 1; i++)
            borders[i] = this.mClusters[i].getMaximum();

        return borders;
    }

    /**
     * Returns the sizes of the clusters. How much elements are there in each
     * cluster.
     * 
     * @return array of ints, each of them representing a cluster's size
     */
    public int[] getClusterSizes() {
        int i = 0, s = this.mClusters.length;
        int[] sizes = new int[s];
        for (; i != s; i++)
            sizes[i] = this.mClusters[i].getSize();

        return sizes;
    }

    /**
     * Returns the number of clusters in which the data is divided.
     * 
     * @return number of clusters
     */
    public int getClusterCount() {
        return this.mClusters.length;
    }

    /**
     * Returns the data of a specific cluster.
     * 
     * @param aClusterIndex
     *            index of a cluster
     * @return data contained in that cluster
     */
    public int[] getClusterData(int aClusterIndex) {
        return this.mClusters[aClusterIndex].getData();
    }

    /**
     * Returns the index of the cluster to which a certain value belongs.
     * 
     * @param aValue
     *            value to be classified in a cluster
     * @return the index of the cluster to which the value is classified
     */
    public int getClusterNr(int aValue) {
        int index;
        int[] borders = getBorders();
        if (borders == null || borders.length == 0)
            index = 0;
        else
            index = getIndex(borders, aValue);

        return index;
    }

    /**
     * Initialise the clusters by dividing the data in equal clusters.
     * 
     */
    public void init() {
        int i, count;
        int previousValue;
        int currentValue;
        int length = this.mData.length;
        if (length == 0)
            return;
        java.util.Arrays.sort(this.mData);
        count = 1;
        previousValue = this.mData[0];
        currentValue = previousValue;
        IntVector distinctData = new IntVector(length);
        IntVector distinctDataCounts = new IntVector(length);
        for (i = 1; i != length; i++) {
            currentValue = this.mData[i];
            if (currentValue != previousValue) {
                distinctData.addElement(previousValue);
                distinctDataCounts.addElement(count);
                previousValue = currentValue;
                count = 1;
            } else {
                count++;
            }
        }
        if ((distinctData.size() == 0)
                || (currentValue != distinctData.get(distinctData.size() - 1))) {
            distinctData.addElement(currentValue);
            distinctDataCounts.addElement(count);
        }

        if (distinctData.size() <= this.mK) {
            this.mAllocationNeeded = false;
            this.mClusters = new Cluster[distinctData.size()];
            for (i = 0; i != distinctData.size(); i++)
                this.mClusters[i] = new Cluster(distinctData.get(i),
                        distinctDataCounts.get(i));
        } else {
            int value;
            int clusterIndex = 0;
            this.mAllocationNeeded = true;
            int clusterMaxSize = this.mData.length / this.mK;
            this.mClusters = new Cluster[this.mK];
            for (i = 0; i != this.mK; i++)
                this.mClusters[i] = new Cluster(distinctData.size());
            for (i = 0; i != distinctData.size(); i++) {
                value = distinctData.get(i);
                count = distinctDataCounts.get(i);
                if (((this.mClusters[clusterIndex].getSize() + count) <= clusterMaxSize)
                        || (clusterIndex == this.mK - 1))
                    this.mClusters[clusterIndex].add(value, count);
                else
                    this.mClusters[++clusterIndex].add(value, count);
            }
        }
    }

    /**
     * Start allocating values to its most nearby cluster.
     * 
     * @return how much value points are switched from one cluster to another
     */
    private int allocate() {
        // int nrOfData = this.mData.length;
        int changes = 0;
        // int size;
        int minDistance;
        int minDistanceCluster = 0;
        int distance;
        int sample;
        int i, j, l;

        for (i = 0; i != this.mK; i++) {
            minDistanceCluster = i;
            for (j = 0; j != this.mClusters[i].getDistinct(); j++) {
                sample = this.mClusters[i].getValue(j);
                minDistance = distance(sample, this.mClusters[i].getMean());
                for (l = 0; l != this.mK; l++) {
                    distance = distance(sample, this.mClusters[l].getMean());
                    if (distance < minDistance) {
                        minDistance = distance;
                        minDistanceCluster = l;
                    }
                }
                if (minDistanceCluster != i) {
                    changes += 1;
                    switchCluster(i, minDistanceCluster, j);
                    i = 0;
                    break;
                }
            }
        }
        return changes;
    }

    /**
     * Switch a value from one cluster to another.
     * 
     * @param aOldClusterIndex
     *            index of the original cluster
     * @param aNewClusterIndex
     *            index of the new cluster
     * @param aPosition
     *            position of the value in the original cluster
     */
    private void switchCluster(int aOldClusterIndex, int aNewClusterIndex,
            int aPosition) {
        Cluster oldCluster = this.mClusters[aOldClusterIndex];
        Cluster newCluster = this.mClusters[aNewClusterIndex];

        int value = oldCluster.getValue(aPosition);
        int count = oldCluster.getCount(aPosition);
        newCluster.add(value, count);
        oldCluster.remove(aPosition);
    }

    /**
     * Helper method : it calculates the distance between two ints a and b as
     * (|a-b|), typically used to determine the distance between an object and
     * the mean of a cluster. In it present implementation only 1-dimensional
     * samples are supported.
     * 
     * For multi-dimensional situations other algorithms should be used, like
     * the euclidean or mahalanobis distance. The input parameters should the be
     * represented by int[].
     * 
     */
    private int distance(int a, int b) {
        int distance = a - b;
        if (distance < 0)
            return -distance;
        else
            return distance;
    }

    /**
     * Returns the position of a value in a sorted array.
     * 
     * @param aArray
     *            sorted array of ints
     * @param aValue
     *            value to be looked for its position in the array
     * @return index of the value in the sorted array
     */
    private int getIndex(int[] aArray, int aValue) {
        int i, size;
        for (i = 0, size = aArray.length; i != size; i++) {
            if (aValue <= aArray[i])
                return i;
        }

        return size;
    }
}
