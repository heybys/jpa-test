package com.heybys.optimusamicus.common.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

  @Column(updatable = false)
  private String createdBy;

  @Column(updatable = false)
  private String createdAt;

  private String modifiedBy;

  private String modifiedAt;

  @PrePersist
  public void prePersist() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
    String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    createdAt = formattedNow;
    modifiedAt = formattedNow;
  }

  @PreUpdate
  public void preUpdate() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

    modifiedAt = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
