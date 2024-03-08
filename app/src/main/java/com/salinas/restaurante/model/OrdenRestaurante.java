package com.salinas.restaurante.model;

public class OrdenRestaurante {
    private String tipo_menu;
    private int cantidad;
    private double precio_unitario;
    private double precio_total;

    public OrdenRestaurante(String tipo_menu, int cantidad, double precio_unitario) {
        this.tipo_menu = tipo_menu;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.precio_total = calcularPrecio();
    }

    public String getTipoMenu() {
        return tipo_menu;
    }

    public void setTipoMenu(String tipo_menu) {
        this.tipo_menu = tipo_menu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.precio_total = calcularPrecio();
    }

    public double getPrecioUnitario() {
        return precio_unitario;
    }

    public void setPrecioUnitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
        this.precio_total = calcularPrecio();
    }

    public double getPrecioTotal() {
        return precio_total;
    }

    public double calcularPrecio() {
        double subtotal = cantidad * precio_unitario;
        double descuento = 0.0;
        double total = subtotal;

        if (subtotal >= 25.00) {
            total = subtotal - precio_unitario;
        }

        if (tipo_menu.equals("menú del día")) {
            total = subtotal;
        } else if (tipo_menu.equals("menú infantil")) {
            total = subtotal;
        } else if (tipo_menu.equals("menú vegetariano")) {
            total = subtotal;
        }

        if (descuento > 0) {
            total = total - (total * descuento);
        }

        return total;
    }

    public void aplicarDescuento() {
        double descuento = 0.05;
        double total = getPrecioTotal();

        if (total > 0) {
            total = total - (total * descuento);
            this.precio_total = total;
        }
    }

    public void agregarPostre() {
        double subtotal = cantidad * precio_unitario;
        double total = getPrecioTotal();

        if (subtotal >= 25.00 && total > 0) {
            this.precio_total = total - precio_unitario;
        }
    }
}



