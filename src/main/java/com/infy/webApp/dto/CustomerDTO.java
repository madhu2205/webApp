package com.infy.webApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

   private Integer customerId;

   @NotBlank(message = "{customer.name.required}")
   @Size(min = 2, max = 50, message = "{customer.name.size}")
   private String name;

   @NotBlank(message = "{customer.email.required}")
   @Email(message = "{customer.email.invalid}")
   private String email;
}
