@XmlSchema
(
	namespace="http://www.kontron.com/qdw/xml",
	elementFormDefault=XmlNsForm.QUALIFIED,
	xmlns=
	{
		@XmlNs(prefix="pre", namespaceURI="http://www.kontron.com/qdw/xml"),
		@XmlNs(prefix="xs", namespaceURI="http://www.w3.org/2001/XMLSchema")
	}
)
package com.kontron.qdw.dto.base;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;