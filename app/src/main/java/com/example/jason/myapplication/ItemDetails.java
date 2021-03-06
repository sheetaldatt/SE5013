package com.example.jason.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class ItemDetails extends AppCompatActivity {


    CartList cart = new CartList();
    List<Object> cartList = new ArrayList<>();

   // MenuItem item;
    private String itemName;
    private double itemPrice;
    Object item;




    CartItem cartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_item);

        final Button add_cart_button = findViewById(R.id.add_cart_button);

        System.out.println("This is where the add to cart button is" );
        // This gets the current Cart Array
       // cartList = cart.getCart();





        add_cart_button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {

                // This adds items to the array
                cartList.add(cartItem);
                // This sets the current array.
                System.out.println("Item Added: Cart Size: "+cartList.size());
                cart.setCart(cartList);
            }
        });

        System.out.println("Oncreate done");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        this.item = item;
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setObject(Object item){
        System.out.println("setObject/ItemDetails");
        System.out.println(itemName);
        System.out.println(itemPrice);
        this.item = item;
    }

    public void printHello(){
        System.out.println("Hello");
    }

    public void setItem(CartItem item){
        this.cartItem = item;
    }


//    This is item selection output

}
