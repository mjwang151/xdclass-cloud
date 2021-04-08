package com.xdclass.pojo;



import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GatewayRoute {
	private static final long serialVersionUID = 7873925821975363979L;
	private String id;
	private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders;
    private String status;
    /**
     * 创建人
     */
    private String inputUser;

    /**
     * 创建时间
     */
    private LocalDate inputTime;
    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDate updateTime;
}
