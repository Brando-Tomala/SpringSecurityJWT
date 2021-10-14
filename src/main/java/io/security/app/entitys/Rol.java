package io.security.app.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;
import static javax.persistence.FetchType.*;

/**
 * @author Brando Tomala
 * @version 1.0
 * @since 01/10/2021
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
}
