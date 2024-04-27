package com.hcl.hackathon.demo.controller;

import com.hcl.hackathon.demo.constants.StatusCode;
import com.hcl.hackathon.demo.domain.BaseResponse;
import com.hcl.hackathon.demo.domain.PagingResponse;
import com.hcl.hackathon.demo.domain.position.TradeRequest;
import com.hcl.hackathon.demo.domain.position.TradeResponse;
import com.hcl.hackathon.demo.domain.position.PositionResponse;
import com.hcl.hackathon.demo.service.PositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class PositionController {

  private PositionService positionService;

  private static final String REQUEST_PAGE_SIZE_DEFAULT = "20";

  @GetMapping(value = "/portfolios/{customerId}/positions")
  public ResponseEntity<BaseResponse<PagingResponse<List<PositionResponse>>>> getPositions(
      @PathVariable final UUID customerId,
      @RequestParam(name = "page", defaultValue = "0") @Min(0) final int pageNumber,
      @RequestParam(name = "size", defaultValue = REQUEST_PAGE_SIZE_DEFAULT) @Min(1) final int pageSize) {
    log.info("getPositions, customerId: {}", customerId);
    var myPositions = positionService.findMyPositions(customerId, pageNumber, pageSize);
    var pagingResponse = PagingResponse.of(myPositions, PagingResponse.Paging.of(pageNumber, pageSize));
    var response = BaseResponse.of(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDesc(), pagingResponse);
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/portfolios/{customerId}/positions")
  public ResponseEntity<BaseResponse<TradeResponse>> trade(
      @PathVariable final UUID customerId,
      @RequestBody @Valid TradeRequest tradeRequest) {
    tradeRequest.setCustomerId(customerId);
    log.info("Start trade, customerId: {}, detail: {}", customerId, tradeRequest);
    var position = positionService.trade(tradeRequest);

    var response = BaseResponse.of(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getDesc(), position);
    return ResponseEntity.ok(response);
  }
}
