package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements BaseDAO<Orders>{

    Connection conn;

    public OrderDAO(){
        conn = DBConnection.getConnection();
    }


    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }

    @Override
    public Orders getId(int id) {
        return null;
    }

    @Override
    public List<Orders> getAll() {
        return null;
    }

    public List<OrderDetails> getAllCustomerOrders(int id){
        String sql = "SELECT * FROM orders AS o INNER JOIN order_details AS od ON o.OrderID = od.OrderID INNER JOIN Products AS p ON od.ProductID = p.ProductID WHERE o.CustomerID = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            List<OrderDetails> order_details = new ArrayList<>();
            while(rs.next()){
                //ORDERDETAIL
                OrderDetails od = new OrderDetails();
                od.setOrder_detail_id(rs.getInt("OrderDetailID"));

                //ORDER
                Orders order = new Orders();
                order.setOrder_id(rs.getInt("OrderID"));
                Customers customers = new Customers();
                customers.setCustomer_id(rs.getInt("CustomerID"));
                order.setCustomer(customers);
                order.setEmployee(rs.getInt("EmployeeID"));
                order.setOrder_date(rs.getString("OrderDate"));
                order.setShipper(rs.getInt("ShipperID"));

                od.setOrder(order);

                //PRODUCTS
                Products product = new Products();
                product.setProduct_id(rs.getInt("ProductID"));
                product.setProduct_name(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setUnit(rs.getString("unit"));

                Category category = new Category();
                category.setCategory_id(rs.getInt("CategoryID"));

                Suppliers supplier = new Suppliers();
                supplier.setSupplier_id(rs.getInt("SupplierID"));

                product.setSupplier(supplier);
                product.setCategory(category);

                od.setProducts(product);

                od.setQuantity(rs.getInt("Quantity"));


                order_details.add(od);
            }
            return order_details;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}