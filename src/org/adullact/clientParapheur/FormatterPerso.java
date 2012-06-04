package org.adullact.clientParapheur;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatterPerso extends Formatter {

	@Override
	public String format(LogRecord record) {
		  StringBuffer s = new StringBuffer(1000);
	      s.append(formatMessage(record) + "\n");
	      return s.toString();
	}
}