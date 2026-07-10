package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a placed food order.
 *
 * Design note for the assignment:
 * This class works, but its construction API is intentionally awkward.
 * The long constructor mixes required fields, optional fields, defaults,
 * validation, and pricing flags. Students should refactor this design without
 * changing the observable behavior of the program.
 */
public class Order {
    public static final double DELIVERY_FEE = 80.0;
    public static final double RUSH_FEE = 120.0;
    public static final double GIFT_WRAP_FEE = 50.0;

    private final String orderId;
    private final String customerName;
    private final String phone;
    private final DeliveryType deliveryType;
    private final String deliveryAddress;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime scheduledTime;
    private final String couponCode;
    private final boolean giftWrap;
    private final boolean cutleryRequired;
    private final int loyaltyPointsToRedeem;
    private final boolean rushOrder;
    private final List<OrderItem> items;
    private final String specialInstructions;

    // public Order(String orderId,
    //              String customerName,
    //              String phone,
    //              DeliveryType deliveryType,
    //              String deliveryAddress,
    //              PaymentMethod paymentMethod,
    //              LocalDateTime scheduledTime,
    //              String couponCode,
    //              boolean giftWrap,
    //              boolean cutleryRequired,
    //              int loyaltyPointsToRedeem,
    //              boolean rushOrder,
    //              List<OrderItem> items,
    //              String specialInstructions) {
    //     this.orderId = requireNonBlank(orderId, "Order id");
    //     this.customerName = requireNonBlank(customerName, "Customer name");
    //     this.phone = requireNonBlank(phone, "Phone");
    //     this.deliveryType = deliveryType != null ? deliveryType : DeliveryType.PICKUP;
    //     this.paymentMethod = paymentMethod != null ? paymentMethod : PaymentMethod.CASH;
    //     this.scheduledTime = scheduledTime;
    //     this.couponCode = couponCode != null ? couponCode.trim().toUpperCase() : "";
    //     this.giftWrap = giftWrap;
    //     this.cutleryRequired = cutleryRequired;
    //     this.loyaltyPointsToRedeem = Math.max(0, loyaltyPointsToRedeem);
    //     this.rushOrder = rushOrder;
    //     this.specialInstructions = specialInstructions != null ? specialInstructions.trim() : "";

    //     if (this.deliveryType == DeliveryType.DELIVERY) {
    //         this.deliveryAddress = requireNonBlank(deliveryAddress, "Delivery address");
    //     } else {
    //         this.deliveryAddress = deliveryAddress != null ? deliveryAddress.trim() : "";
    //     }

    //     Objects.requireNonNull(items, "Items cannot be null");
    //     if (items.isEmpty()) {
    //         throw new IllegalArgumentException("Order must contain at least one item");
    //     }
    //     this.items = Collections.unmodifiableList(new ArrayList<>(items));
    // }

    // public Order(String orderId, String customerName, String phone, List<OrderItem> items) {
    //     this(orderId, customerName, phone, DeliveryType.PICKUP, "", PaymentMethod.CASH,
    //             null, "", false, true, 0, false, items, "");
    // }

    private Order(Builder builder) {
        this.orderId = requireNonBlank(builder.orderId, "Order id");
        this.customerName = requireNonBlank(builder.customerName, "Customer name");
        this.phone = requireNonBlank(builder.phone, "Phone");
        this.deliveryType = builder.deliveryType;
        this.paymentMethod = builder.paymentMethod;
        this.scheduledTime = builder.scheduledTime;
        this.couponCode = builder.couponCode;
        this.giftWrap = builder.giftWrap;
        this.cutleryRequired = builder.cutleryRequired;
        this.loyaltyPointsToRedeem = Math.max(0, builder.loyaltyPointsToRedeem);
        this.rushOrder = builder.rushOrder;
        this.specialInstructions = builder.specialInstructions;

        if (this.deliveryType == DeliveryType.DELIVERY) {
            this.deliveryAddress = requireNonBlank(builder.deliveryAddress, "Delivery address");
        } else {
            this.deliveryAddress = builder.deliveryAddress;
        }

        Objects.requireNonNull(builder.items, "Items cannot be null");
        if (builder.items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.items = Collections.unmodifiableList(new ArrayList<>(builder.items));
    }

    public static class Builder {
        // Required fields with default values for optional fields
        private String orderId;
        private String customerName;
        private String phone;
        private DeliveryType deliveryType = DeliveryType.PICKUP;
        private String deliveryAddress = "";
        private PaymentMethod paymentMethod = PaymentMethod.CASH;
        private LocalDateTime scheduledTime = null;
        private String couponCode = "";
        private boolean giftWrap = false;
        private boolean cutleryRequired = true;
        private int loyaltyPointsToRedeem = 0;
        private boolean rushOrder = false;
        private List<OrderItem> items;
        private String specialInstructions = "";

        // Builder constructor with required fields
        public Builder(String orderId, String customerName, String phone, List<OrderItem> items) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.phone = phone;
            this.items = items;
        }

        // Builder methods for optional fields
        public Builder deliveryType(DeliveryType deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder deliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder scheduledTime(LocalDateTime scheduledTime) {
            this.scheduledTime = scheduledTime;
            return this;
        }

        public Builder couponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        public Builder giftWrap(boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public Builder cutleryRequired(boolean cutleryRequired) {
            this.cutleryRequired = cutleryRequired;
            return this;
        }

        public Builder loyaltyPointsToRedeem(int loyaltyPointsToRedeem) {
            this.loyaltyPointsToRedeem = loyaltyPointsToRedeem;
            return this;
        }

        public Builder rushOrder(boolean rushOrder) {
            this.rushOrder = rushOrder;
            return this;
        }

        public Builder specialInstructions(String specialInstructions) {
            this.specialInstructions = specialInstructions;
            return this;
        }

        // Final build method to create an Order instance
        public Order build() {
            // return new Order(orderId, customerName, phone, deliveryType, deliveryAddress,
            //         paymentMethod, scheduledTime, couponCode, giftWrap, cutleryRequired,
            //         loyaltyPointsToRedeem, rushOrder, items, specialInstructions);
            return new Order(this);
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public boolean isCutleryRequired() {
        return cutleryRequired;
    }

    public int getLoyaltyPointsToRedeem() {
        return loyaltyPointsToRedeem;
    }

    public boolean isRushOrder() {
        return rushOrder;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public double getDiscount() {
        double couponDiscount = 0.0;
        if ("WELCOME10".equals(couponCode)) {
            couponDiscount = getSubtotal() * 0.10;
        } else if ("FAMILY15".equals(couponCode) && getSubtotal() >= 1000.0) {
            couponDiscount = getSubtotal() * 0.15;
        }

        double loyaltyDiscount = Math.min(loyaltyPointsToRedeem, 100);
        return couponDiscount + loyaltyDiscount;
    }

    public double getServiceCharges() {
        double charges = 0.0;
        if (deliveryType == DeliveryType.DELIVERY) {
            charges += DELIVERY_FEE;
        }
        if (rushOrder) {
            charges += RUSH_FEE;
        }
        if (giftWrap) {
            charges += GIFT_WRAP_FEE;
        }
        return charges;
    }

    public double getTotal() {
        return Math.max(0.0, getSubtotal() + getServiceCharges() - getDiscount());
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return trimmed;
    }
}
