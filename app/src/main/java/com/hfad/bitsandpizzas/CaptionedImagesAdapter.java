package com.hfad.bitsandpizzas;

// The RecyclerView class is in a support library.
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

/**
 *  Created by jm on 02/01/17.
    The adapter has two main jobs: to create each of the views that are visible
    within the recycler view, and to configure the view to match a piece of data.
    In our case, the recycler view needs to display a list of cards, each containing an image view and a text view.
    This means that the adapter needs to create views for these items, and replace their contents when each
    item in the data set is no longer visible.
 */
class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{

    // We’ll use these variables to hold the pizza data.
    private String[] captions;
    private int[] imageIds;

    private Listener listener;

    public static interface Listener {
        public void onClick(int position);
    }

    //Provide a reference to the views used in the recycler view. You need to define the ViewHolder. We’ll do this on the next page.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Define the view holder
        private CardView cardView;
        // Our recycler view needs to display CardViews, so we specify that our ViewHolder contains
        // CardViews. If you want to display another type of data in the recycler view, you define it here
        // Each ViewHolder will display a CardView.
        public ViewHolder(CardView v) {
            // ViewHolder superclass includes metadata such as the item’s position in the recycler view, and you need this
            // for the adapter to work properly.
            super(v);
            cardView=v;
        }
    }

    // Pass data to the adapter in its constructor.
    public CaptionedImagesAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    // The onCreateViewHolder() method is used to create the views,
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        // You need to tell it which layout to use for each view holder in the adapter’s
        // Use our layout for the CardViews.
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    // The onBindViewHolder() is used to set the values inside the views.
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Set the values inside the given view
        CardView cardView = holder.cardView;
        // Display the image in the ImageView.
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        // .getDrawable(int) is deprecated !!
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        // Display the caption in the TextView.
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
    /**
    // We’ll use these variables to hold the pizza data.
    private String[] captions;
    private int[] imageIds;

    //Provide a reference to the views used in the recycler view. You need to define the ViewHolder. We’ll do this on the next page.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Define the view holder
        private CardView cardView;

        // Our recycler view needs to display CardViews, so we specify that our ViewHolder contains
        // CardViews. If you want to display another type of data in the recycler view, you define it here
        // Each ViewHolder will display a CardView.
        public ViewHolder(CardView v) {
            // ViewHolder superclass includes metadata such as the item’s position in the recycler view, and you need this
            // for the adapter to work properly.
            super(v);
            cardView = v;
        }
    }

    // Pass data to the adapter in its constructor.
    public CaptionedImagesAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    // The onCreateViewHolder() method is used to create the views,
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new view
        // You need to tell it which layout to use for each view holder in the adapter’s
        // Use our layout for the CardViews.
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    // The onBindViewHolder() is used to set the values inside the views.
    public void onBindViewHolder(ViewHolder holder, int position){
        //Set the values inside the given view
        CardView cardView = holder.cardView;
        // Display the image in the ImageView.
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        // .getDrawable(int) is deprecated !!
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        // Display the caption in the TextView.
        textView.setText(captions[position]);
    }


    @Override
    // The getItemCount() method is used to return the number o items in the data set
    public int getItemCount(){
        //Return the number of items in the data set
        return captions.length;
    }
    */
}