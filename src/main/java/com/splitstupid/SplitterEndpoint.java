package com.splitstupid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Api(tags = {"Splitter"})
public class SplitterEndpoint {

    private final Splitter splitter;

    public SplitterEndpoint(final Splitter splitter) {
        this.splitter = splitter;
    }

    @ApiOperation(
        value = "Splits the expenses between the given participants according to their participation.",
        httpMethod = "PUT", nickname = "splitExpenses")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Expenses were successfully split.", response = String.class),
    })
    @PutMapping("/splitExpenses")
    public String splitExpenses(
        @ApiParam(value = "List of expenses.", required = true) @RequestBody final Set<Expense> expenses) {
        return splitter.split(expenses);
    }
}
