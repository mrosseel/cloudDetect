package media.image.consumer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;

import media.image.CloudFileResult;
import media.image.CloudResultMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Boltwood1ScoringSubConsumer implements
		SubConsumer<CloudFileResult> {
	private static Log log = LogFactory
			.getLog(Boltwood1ScoringSubConsumer.class);
	DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-ddHH:mm:ss");

	@Override
	public void consume(CloudFileResult content) {
		try {
			setCloudStatus(content.getFileData(), content.getMetaData());
		} catch (Throwable e) {
			log.error("Error scoring a boltwood file: "+content.getFileData(),e);
		}
	}

	private void setCloudStatus(String fileContents, CloudResultMetaData meta) throws Throwable {
		int result = -1;
		BufferedReader reader = new BufferedReader(new StringReader(fileContents));
		String line = reader.readLine();
		log.debug("Line is " + line + "for feed " + meta.getFeedId());
		StringTokenizer tokkie = new StringTokenizer(line, " ");
		StringBuffer buffer = new StringBuffer(tokkie.nextToken());
		buffer.append(tokkie.nextToken());
		meta.setDate(fmt.parseDateTime(buffer.toString()).toDate());
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		tokkie.nextToken();
		result = Integer.valueOf(tokkie.nextToken()).intValue();
		meta.setResult(result);
		log.debug("Boltwood cloud status has result" + result);
	}
}
