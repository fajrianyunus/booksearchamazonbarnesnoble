package com.fajrianyunus.booksearchamazonbarnesnoble;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class BookSearchAmazonBarnesNoble extends Activity {
	
	private EditText isbnIssnEditText;
	private Button searchAmazonButton;
	private Button barnesnobleButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_amazon_barnes_noble);
        
        isbnIssnEditText = (EditText) this.findViewById(R.id.isbnIssnTextfield);
        
        searchAmazonButton = (Button) this.findViewById(R.id.amazonButton);
        
        searchAmazonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String isbnIssn = isbnIssnEditText.getText().toString();
            	
            	if (isbnIssn.matches("^\\s*$")) {
            		Toast.makeText(BookSearchAmazonBarnesNoble.this, "Please enter ISBN or ISSN", Toast.LENGTH_SHORT).show();
            	} else {
            		String url = "http://www.amazon.com/gp/search/ref=sr_adv_b/?field-isbn="+isbnIssn.trim();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);      		
            	}
            }
        });
        
        barnesnobleButton = (Button) this.findViewById(R.id.barnesnobleButton);
        
        barnesnobleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String isbnIssn = isbnIssnEditText.getText().toString();
            	
            	if (isbnIssn.matches("^\\s*$")) {
            		Toast.makeText(BookSearchAmazonBarnesNoble.this, "Please enter ISBN or ISSN", Toast.LENGTH_SHORT).show();
            	} else {
            		Toast.makeText(BookSearchAmazonBarnesNoble.this, "search in barnes noble", Toast.LENGTH_SHORT).show();
            		String url = "http://www.barnesandnoble.com/s?keyword="+isbnIssn.trim();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);             		         		
            	}
            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_search_amazon_barnes_noble, menu);
        return true;
    }

    
}
