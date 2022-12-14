package com.alkemy.ong.infrastructure.rest.mapper.organization;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.mapper.slide.SlideWithTextMapper;
import com.alkemy.ong.infrastructure.rest.response.organization.GetOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrganizationMapper {

  private final SlideWithTextMapper slideWithTextMapper;
  private final SocialMediaMapper socialMediaMapper;

  public GetOrganizationResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    GetOrganizationResponse getOrganizationResponse = new GetOrganizationResponse();
    getOrganizationResponse.setName(organization.getName());
    getOrganizationResponse.setImage(organization.getImage());
    getOrganizationResponse.setPhone(organization.getPhone());
    getOrganizationResponse.setAddress(organization.getAddress());
    getOrganizationResponse.setSlides(slideWithTextMapper.toResponse(organization.getSlides()));
    getOrganizationResponse.setSocialMedia(
        socialMediaMapper.toResponse(organization.getSocialMedia()));
    return getOrganizationResponse;
  }
}