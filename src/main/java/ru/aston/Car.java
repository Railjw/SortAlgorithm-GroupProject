package ru.aston;

import java.util.Objects;

public final class Car {
    private final int power;
    private final String model;
    private final int productionYear;

    private static final int MIN_PRODUCTION_YEAR = 1886;

    private Car(
            final int power,
            final String model,
            final int productionYear
    ) {
        this.power = power;
        this.model = model;
        this.productionYear = productionYear;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }
    
    @Override
    public boolean equals (Object testCarOrNot) {
        if (this == testCarOrNot)
            return true;
        
        if (testCarOrNot == null || this.getClass() != testCarOrNot.getClass())
            return false;
        
        Car testCar = (Car)testCarOrNot;
        boolean carsAreSame = Objects.equals(this.getModel(), testCar.getModel()) && 
                this.getPower()== testCar.getPower() &&
                this.getProductionYear()== testCar.getProductionYear();
        
        return carsAreSame;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getModel(), Integer.toString(this.getPower()), Integer.toString(this.getProductionYear()));
    }

    public static final class Builder {

        private int power;
        private String model;
        private int productionYear;

        public Builder power(final int power) {
            this.power = power;
            return this;
        }

        public Builder model(final String model) {
            this.model = model;
            return this;
        }

        public Builder productionYear(final int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Car build() {
            validate();

            return new Car(
                    power,
                    model,
                    productionYear
            );
        }

        private void validate() {
            if (power <= 0) {
                throw new IllegalArgumentException("Power must be greater than 0");
            }

            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Model cannot be empty");
            }

            if (productionYear < MIN_PRODUCTION_YEAR) {
                throw new IllegalArgumentException(
                        "Production year cannot be earlier than " + MIN_PRODUCTION_YEAR
                );
            }

            //Я добавил, но оно нам надо? Я никому этим валидатором разработку не испорчу?
        }
    }
}
