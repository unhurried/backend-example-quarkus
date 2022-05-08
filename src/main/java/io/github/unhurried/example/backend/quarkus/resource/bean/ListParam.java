package io.github.unhurried.example.backend.quarkus.resource.bean;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class ListParam {
    @DefaultValue("10")
    @QueryParam("size")
    public Integer size;

    @DefaultValue("0")
    @QueryParam("page")
    public Integer page;
}
