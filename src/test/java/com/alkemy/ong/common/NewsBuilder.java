package com.alkemy.ong.common;

import com.alkemy.ong.domain.News;

public class NewsBuilder {

  public static News random() {
    return new News(null, "test", "content", "imageUrl", null);
  }

}