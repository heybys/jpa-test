package com.heybys.optimusamicus.common.entity;

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
  private String createdDate;

  private String lastModifiedBy;

  private String lastModifiedDate;

  @PrePersist
  public void prePersist() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
    String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    createdDate = formattedNow;
    lastModifiedDate = formattedNow;
  }

  @PreUpdate
  public void preUpdate() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

    lastModifiedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
