package com.iisi.test.brian.demo;

import javax.xml.bind.annotation.*;
import java.util.List;

public class Xmlobj {
    @XmlAttribute(name = "version")
    String version;
    @XmlAttribute(name = "encoding")
    String encoding;

    @XmlRootElement(name = "note")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Note {
        @XmlElement(name = "to")
        String to;
        @XmlElement(name = "from")
        String from;
        @XmlElement(name = "heading")
        String heading;
        @XmlElement(name = "body")
        String body;
        @XmlElement(name = "attachs")
        Attachs attachs;
    }

    @XmlRootElement(name = "attachs")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Attachs {
        @XmlElement(name = "attach")
        List<Attach> attaches;
    }

    @XmlRootElement(name = "attach")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Attach {
        @XmlElement(name = "name", required = false)
        String name;
        @XmlElement(name = "content", required = false)
        String content;
    }
}
