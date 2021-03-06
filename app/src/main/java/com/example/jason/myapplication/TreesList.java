package com.example.jason.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class TreesList extends AppCompatActivity {

    Button addToCart;
    Button toCartout;
    CartScreen cartScreen = new CartScreen();
    CartList cart = new CartList();
    List<Object> cartList = new ArrayList<>();
    CartItem cartItem = new CartItem();
    private List<Trees> trees = new ArrayList<>();

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_viewlist);

        populateTreesList();
        populateTreeListView();
        onClickCallTree();


        //cartList = cart.getCart();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void populateTreesList(){
        trees.add(new Trees(
                "Pohu",
                "red stuff 1",
                5,
                1000.0,
                R.drawable.plantatreelogo)
        );
        trees.add(new Trees(
                "Kauri",
                "Big and cool",
                10,
                2500.0,
                R.drawable.kowhai)
        );
        trees.add(new Trees(
                "Fern",
                "red stuff 2",
                20,
                8000.0,
                R.drawable.pohutukawa)
        );
        trees.add(new Trees(
                "Apple tree",
                "red stuff 3",
                2,
                200.0,
                R.drawable.shovel)
        );
        trees.add(new Trees(
                "Carrot",
                "red stuff 4",
                500,
                3240.0,
                R.drawable.kowhai)
        );
    }

    //This method populate the ListView for the Trees
    private void populateTreeListView() {
        ArrayAdapter<Trees> adapter = new MyListAdapter();
        ListView list = findViewById(R.id.treesListView);
        list.setAdapter(adapter);
    }


    /******************************************
     *This is where we show the selected tree
     ******************************************/

    private void onClickCallTree(){
        ListView list = findViewById(R.id.treesListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Trees clickedTree = trees.get(position);

                final String treeName = clickedTree.getTreeName();
                String treeDescription = clickedTree.getDescription();
                final double treePrice = clickedTree.getPrice();
                int treePic = clickedTree.getTreePicture();

                setContentView(R.layout.view_selected_item);
                addToCart = findViewById(R.id.add_cart_button);
                toCartout =findViewById(R.id.cart_button);

                addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Test for button");
                        cartItem.setItemName(treeName);
                        cartItem.setItemPrice(treePrice);

                        cartList = cart.getCart();
                        System.out.println("Product before add Cart Size : "+cartList.size());
                        cartList.add(cartItem);
                        System.out.println("Product after add Cart Size : "+cartList.size());
                        cart.setCart(cartList);


                        System.out.println(cartItem.getItemName());
                        System.out.println(cartItem.getItemPrice());
                    }
                });

                toCartout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartScreen.setCartList(cartList);
                        Intent intent = new Intent(TreesList.this, CartScreen.class);
                        startActivity(intent);
                    }
                });


                TextView treeHeight = findViewById(R.id.height);
                treeHeight.setText("1.83m");

                TextView treeN = findViewById(R.id.itemName);
                treeN.setText(treeName);

                TextView treeDesc = findViewById(R.id.itemDescription);
                treeDesc.setText(treeDescription);

                ImageView treepic = findViewById(R.id.itemPic);
                treepic.setImageResource(treePic);

                TextView treeP = findViewById(R.id.itemPrice);
                treeP.setText("$ "+valueOf(treePrice));
                

            }
        });
    }


    //Trees
    private class MyListAdapter extends ArrayAdapter<Trees>{
        public MyListAdapter() {
            super(TreesList.this, 0, trees);
        }


        /******************************************
         *This is where we print the item list view
         ******************************************/

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            // make sure we have a view to work with
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_list_format, parent, false);
            }

            //Find tree
            Trees currentTree = trees.get(position);

            //Picture of tree:
            ImageView imageview = itemView.findViewById(R.id.itemPic1);
            imageview.setImageResource(currentTree.getTreePicture());

            // Description of tree:
            TextView description = itemView.findViewById(R.id.itemDescription1);
            description.setText(currentTree.getDescription());

            // Name of tree:
            TextView name = itemView.findViewById(R.id.itemName1);
            name.setText(currentTree.getTreeName());

            // Price of tree:
            TextView price = itemView.findViewById(R.id.itemPrice1);
            price.setText("$ " + currentTree.getPrice());

            return itemView;
        }
    }
}
