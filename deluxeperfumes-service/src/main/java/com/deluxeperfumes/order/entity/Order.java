package com.deluxeperfumes.order.entity;

import com.deluxeperfumes.entity.BaseEntity;
import com.deluxeperfumes.perfume.entity.Perfume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order_table")
public class Order extends BaseEntity implements Observer {

    private String identifier;
    private String username;
    private String orderCreatedMessage;
    private double orderPrice;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "order")
    private List<Perfume> perfumes;

    @PreRemove
    private void preRemove() {
        for(Perfume entity : perfumes) {
            entity.setOrder(null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setOrderCreatedMessage(arg.toString());
    }
}
