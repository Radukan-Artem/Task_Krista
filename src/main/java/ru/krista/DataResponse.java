package ru.krista;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataResponse
{
    @XmlElement(name = "item")
    private List<Object> items;

    public DataResponse()
    {
    }

    public DataResponse(List<Object> items)
    {
        this.items = items;
    }

    public List<Object> getItems() { return items; }
    public void setItems(List<Object> items) { this.items = items; }
}