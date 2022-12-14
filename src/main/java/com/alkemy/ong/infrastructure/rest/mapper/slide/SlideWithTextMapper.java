package com.alkemy.ong.infrastructure.rest.mapper.slide;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.slide.SlideWithTextResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlideWithTextMapper {

  public List<SlideWithTextResponse> toResponse(List<Slide> slides) {
    if (slides == null || slides.isEmpty()) {
      return Collections.emptyList();
    }
    List<SlideWithTextResponse> slideWithTextResponses = new ArrayList<>(slides.size());
    for (Slide slide : slides) {
      slideWithTextResponses.add(toResponse(slide));
    }
    return slideWithTextResponses;
  }

  public SlideWithTextResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    return new SlideWithTextResponse(slide.getImageUrl(), slide.getOrder(), slide.getText());
  }

}
