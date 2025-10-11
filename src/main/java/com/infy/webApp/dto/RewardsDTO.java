package com.infy.webApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RewardsDTO {

   private Integer rewardId;

   @NotNull(message = "{reward.transactionId.required}")
   private Integer transactionId;

   @NotNull(message = "{reward.points.required}")
   @Min(value = 0, message = "{reward.points.min}")
   private Integer pointsEarned;
}
