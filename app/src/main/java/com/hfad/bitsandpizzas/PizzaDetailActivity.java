package com.hfad.bitsandpizzas;

/**
 * Created by jm on 02/01/17.
 */


    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.ImageView;
    import android.widget.ShareActionProvider;
    import android.widget.TextView;

    public class PizzaDetailActivity extends Activity {

        // We’ll use this constant to pass the ID of the pizza as extra information in the intent
        public static final String EXTRA_PIZZANO = "pizzaNo";
        private ShareActionProvider shareActionProvider;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pizza_detail);

            //Enable the Up button
            getActionBar().setDisplayHomeAsUpEnabled(true);

            // Display details of the pizza
            // Get the pizza the user chose from the intent.
            int pizzaNo = (Integer)getIntent().getExtras().get(EXTRA_PIZZANO);
            String pizzaName = Pizza.pizzas[pizzaNo].getName();
            TextView textView = (TextView)findViewById(R.id.pizza_text);
            textView.setText(pizzaName);
            int pizzaImage = Pizza.pizzas[pizzaNo].getImageResourceId();
            ImageView imageView = (ImageView)findViewById(R.id.pizza_image);
            // Use the pizza ID to populate the TextView and ImageView.
            // .getDrawable(int) is deprecated !!
            imageView.setImageDrawable(getResources().getDrawable(pizzaImage));
            imageView.setContentDescription(pizzaName);
        }

        @Override
        //  Back in Chapter 9, we created a menu resource file that describes items we wanted to add to the action bar. We’ll
        //  use the onCreateOptionsMenu() to add these items to PizzaDetailActivity’s action bar.
        public boolean onCreateOptionsMenu(Menu menu) {
            // Add items in the menu resource file to the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);

            // The menu resource file describes a Share action that we can use to
            // share information. We’ll add an intent to the Share action that will
            //share the name o the pizza the user has selected.
            TextView textView = (TextView)findViewById(R.id.pizza_text);
            CharSequence pizzaName = textView.getText();
            MenuItem menuItem = menu.findItem(R.id.action_share);
            shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, pizzaName);
            // Set the default text to share in the Share action.
            shareActionProvider.setShareIntent(intent);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_create_order:
                    // Start OrderActivity when the user clicks on it in the action bar.
                    Intent intent = new Intent(this, OrderActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
}
