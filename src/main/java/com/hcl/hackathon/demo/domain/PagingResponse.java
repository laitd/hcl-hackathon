package com.hcl.hackathon.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PagingResponse<T> {

  private T content;
  private Paging requestPaging;

  @Data
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor
  public static class Paging {
    private int page;
    private int size;
  }
}

