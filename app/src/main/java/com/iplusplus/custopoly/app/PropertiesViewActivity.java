package com.iplusplus.custopoly.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iplusplus.custopoly.model.gamemodel.element.Player;
import com.iplusplus.custopoly.model.gamemodel.element.PropertyLand;
import com.iplusplus.custopoly.model.gamemodel.util.Color;

import java.util.ArrayList;


public class PropertiesViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_view);

        Intent intent = getIntent();

        Player currentPlayer = (Player)intent.getSerializableExtra("currentPlayer");
        ArrayList<PropertyLand> properties = (ArrayList<PropertyLand>)intent.getSerializableExtra("propertiesList");
        ArrayList<Integer> imageIds = intent.getIntegerArrayListExtra("imageIdsList");
        
        buildPropertiesViewFromInformation(currentPlayer, properties, imageIds);
    }

    private void buildPropertiesViewFromInformation(Player currentPlayer, ArrayList<PropertyLand> properties, ArrayList<Integer> imageIds)
    {
        LinearLayout propertiesContainer = (LinearLayout)findViewById(R.id.propertiesContainerLayout);

        //Add an image to the porpertiesContainerLayout (its an horizontal scrollview)
        int i = 0;
        for(PropertyLand prop: properties)
        {
            ImageView propertyImageView = new ImageView(this);
            //Set the image resource id
            propertyImageView.setImageResource(imageIds.get(i));
           // propertyImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            propertiesContainer.addView(propertyImageView);

            final PropertyLand attachedProp = prop;
            propertyImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayPropertyInformation(attachedProp);
                }
            });

            i++;
        }

        //Initial information
        if(!properties.isEmpty()) {
            displayPropertyInformation(properties.get(0));
        }
    }

    void displayPropertyInformation(PropertyLand property)
    {
        //Get the text views that will be modifed
        TextView propertyNameView = (TextView)findViewById(R.id.nameTextView);
        TextView propertyValueView = (TextView)findViewById(R.id.valueTextView);
        TextView propertyRentView = (TextView)findViewById(R.id.rentValueTextView);

        //Set values
        propertyNameView.setText(property.getName());
        propertyValueView.setText(Integer.toString(property.getPrice()));
        propertyRentView.setText(Integer.toString(property.getRentInfo().getBaseRent()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_property_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
