package org.epam.com.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Rider {

  @Getter
  @Setter
  private String name;
}
