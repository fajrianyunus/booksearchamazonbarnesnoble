package com.fajrianyunus.booksearchamazonbarnesnoble;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookSearchAmazonBarnesNoble extends Activity {
	
	private EditText isbnIssnEditText;
	private Button searchAmazonButton;
	private Button barnesnobleButton;
	private Button barcodeScannerButton;
	
	private final int ZXING_INTENT_CODE = 0;
	
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
            		Toast.makeText(BookSearchAmazonBarnesNoble.this, "Please enter the ISBN or ISSN", Toast.LENGTH_SHORT).show();
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
            		Toast.makeText(BookSearchAmazonBarnesNoble.this, "Please enter the ISBN or ISSN", Toast.LENGTH_SHORT).show();
            	} else {
            		String url = "http://www.barnesandnoble.com/s?keyword="+isbnIssn.trim();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    startActivity(browserIntent);             		         		
            	}
            }
        });
        
        barcodeScannerButton = (Button) this.findViewById(R.id.scanBarcode);
        
        barcodeScannerButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				try {
		            Intent zxingIntent = new Intent("com.google.zxing.client.android.SCAN");
		            startActivityForResult(zxingIntent, ZXING_INTENT_CODE);
		        } catch (ActivityNotFoundException e) {
		            try {
		            	Toast.makeText(BookSearchAmazonBarnesNoble.this, "Barcode scanner is required, you will be prompted to download the barcode scanner", Toast.LENGTH_SHORT).show();
		                Intent zxingMarketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android"));
		                startActivity(zxingMarketIntent);              
		            } catch (ActivityNotFoundException e2) {
		                Toast.makeText(BookSearchAmazonBarnesNoble.this, "Sorry, but Google Play / Android Market is required", Toast.LENGTH_SHORT).show();
		            }
		  
		        }
			}
		});
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == ZXING_INTENT_CODE) {
            if (resultCode == RESULT_OK) {
                String isbnIssn = intent.getStringExtra("SCAN_RESULT");
                String barcodeFormat = intent.getStringExtra("SCAN_RESULT_FORMAT");
                
                
                if (isbnIssn != null) {
                	isbnIssn = isbnIssn.trim();
                	StringBuffer isbnIssnNumberOnlyBfr = new StringBuffer();
                	for (int i = 0 ; i < isbnIssn.length() ; i++) {
                		char c = isbnIssn.charAt(i);
                		if (Character.isDigit(c)) {
                			isbnIssnNumberOnlyBfr.append(c);
                		}
                	}
                	if (isbnIssnNumberOnlyBfr.length() > 0) {
                		isbnIssnEditText.setText(isbnIssnNumberOnlyBfr.toString());
                	} else {
                		Toast.makeText(BookSearchAmazonBarnesNoble.this, "There is no number scanned. Please try again", Toast.LENGTH_SHORT).show();
                	}
                } else {
                	Toast.makeText(BookSearchAmazonBarnesNoble.this, "Sorry, error is occured", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(BookSearchAmazonBarnesNoble.this, "Barcode scanning is cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_search_amazon_barnes_noble, menu);
        return true;
    }

    
}
