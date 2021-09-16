package com.kimmy.ExpenseTrackerREStAPI.entity.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryTotal {
    private String name;
    private Double totalPrice;
}
