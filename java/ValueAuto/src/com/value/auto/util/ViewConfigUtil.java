package com.value.auto.util;

import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.value.auto.metadata.Project;

/**
 * load view config
 *
 */
public class ViewConfigUtil {
    
    private static Log log = LogFactory.getLog(ViewConfigUtil.class);

	public static Project parseViewConfig() {
	    Project project = null;
		Mapping mapping = new Mapping();
		try {
			// 1. Load the mapping information from the file
			mapping.loadMapping(ViewConfigUtil.class.getClassLoader().getResource("config/ProjectConfigMapping.xml" ));
			
			// 2. Unmarshal the data
			Unmarshaller unmar = new Unmarshaller(mapping);
			project = (Project)unmar.unmarshal(new InputSource(
			                                   new FileReader(ViewConfigUtil.class.getClassLoader().getResource("config/moduleConfig.xml").getPath())));

	    }
		catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		
		return project;
		
	}
	
}
