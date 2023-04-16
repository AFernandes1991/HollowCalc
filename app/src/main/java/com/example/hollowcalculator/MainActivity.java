package com.example.hollowcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etDisplay, etSolution;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnPlus, btnSub, btnDiv, btnMul, btnClear;
    private Button btnMod, btnNatLog, btnLog, btnRoot, btnPower, btnPi, btnSin, btnCos, btntan, btnFact;
    //private String displayIn = "", displayOut;
    private Boolean isEqualsClicked = false;
    private Boolean isOperatorClicked;
    private LinearLayout aboutLayout, feedbackLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEditTexts();
        initButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settngs:
                Toast.makeText(this, "Eureka!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Intent helpIntent = new Intent(this, HelpActivity.class);
                startActivity(helpIntent);

                break;
            case R.id.feedback:
                feedbackPopup();
                break;
            case R.id.share:
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Try this new amazing calculator.");
                String title = "Share Hollow Calculator with your friends!";
                Intent chooser = Intent.createChooser(sendIntent, title);
                startActivity(chooser);
                break;
            case R.id.about:
                aboutPopup();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }

        return false;
    }

    public void aboutPopupOLD(){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.about_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = 900;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }

    });
    }

    public void aboutPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" About");
        builder.setMessage(" Version 1\nCopyright© 2021-2022 by Hollow\nHollow Calculator is an open-source calculator. ")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                                        }
                }).show();
    }

    public void feedbackPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("What can be better?");
        String[] items = {"Functionality","Looks","Usability","New Features"};
        final ArrayList<String> checkedItemsArray = new ArrayList<String>(); // Array to store text of checkboxes clicked by the user.
        final boolean[] uncheckedItems = {false, false, false, false, false,false};
        builder.setMultiChoiceItems(items, uncheckedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                switch (which) {
                    case 0:
                        if(isChecked) {
                            // If the user checked the item, add it to the selected items
                            checkedItemsArray.add("Functionality");
                        }
                        else if(checkedItemsArray.contains("Functionality")) {
                            // Else, if the item is already in the array, remove it
                            checkedItemsArray.remove("Functionality");
                        }
                        break;
                    case 1:
                        if(isChecked) {
                            // If the user checked the item, add it to the selected items
                            checkedItemsArray.add("Looks");
                        }
                        else if(checkedItemsArray.contains("Looks")) {
                            // Else, if the item is already in the array, remove it
                            checkedItemsArray.remove("Looks");
                        }
                        break;
                    case 2:
                        if(isChecked) {
                            // If the user checked the item, add it to the selected items
                            checkedItemsArray.add("Usability");
                        }
                        else if(checkedItemsArray.contains("Usability")) {
                            // Else, if the item is already in the array, remove it
                            checkedItemsArray.remove("Usability");
                        }
                        break;
                    case 3:
                        if(isChecked) {
                            // If the user checked the item, add it to the selected items
                            checkedItemsArray.add("New Features");
                        }
                        else if(checkedItemsArray.contains("New Features")) {
                            // Else, if the item is already in the array, remove it
                            checkedItemsArray.remove("New Features");
                        }
                        break;
                }
            }
        });
        builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String checkedItems = TextUtils.join(" & ", checkedItemsArray); //Separates items in checkedItemsArray by  ', ' and stores into checkedItems.
                String emailSubject = "Feedback";
                String emailMessage = checkedItems + " has been requested to be improved by this user.";
                if(checkedItems != "") {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "email@email.com", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                    intent.putExtra(Intent.EXTRA_TEXT, emailMessage);
                    startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                }else
                {
                    Toast.makeText(MainActivity.this, "Please select at least one.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    //Initialize all edittexts.
    public void initEditTexts() {
        etDisplay = findViewById(R.id.etDisplay);
        etDisplay.setShowSoftInputOnFocus(false);
        etSolution = findViewById(R.id.etSolution);
        etSolution.setShowSoftInputOnFocus(false);
    }

    //Initialize all buttons.
    public void initButtons() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);
        btnClear = findViewById(R.id.btnClear);


        btnPlus = findViewById(R.id.btnPlus);
        btnSub = findViewById(R.id.btnSub);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);

        btnMod = findViewById(R.id.btnMod);
        btnNatLog = findViewById(R.id.btnNatLog);
        btnLog = findViewById(R.id.btnLog);
        btnRoot = findViewById(R.id.btnRoot);
        btnPower = findViewById(R.id.btnPower);
        btnPi = findViewById(R.id.btnPi);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btntan = findViewById(R.id.btnTan);


        btnFact = findViewById(R.id.btnFact);
    }

    public void updateDisplay(String strToAdd) {


        //Checks the isEqualsClicked boolean flag which is set by the '=' button.
        if (isEqualsClicked == false) {
            String oldString = etDisplay.getText().toString();
            int cursorPosition = etDisplay.getSelectionStart();
            String leftString = oldString.substring(0, cursorPosition);
            String rightString = oldString.substring(cursorPosition);
            etDisplay.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
            etDisplay.setSelection(cursorPosition + strToAdd.length());


            //MxParser code.
            String newString = etDisplay.getText().toString();
            Expression exp = new Expression(newString);
            String finalResult = String.valueOf(exp.calculate());
            if (finalResult.endsWith(".0") || (finalResult.endsWith("NaN"))) {
                finalResult = finalResult.replace(".0", "");
                finalResult = finalResult.replace("NaN", "Invalid Format");
            }
            etSolution.setText(finalResult);

        } else {
            //Goes here if flag is true. Overwrites previous entry in the edittext after '=' button is clicked.
            etDisplay.setText("");

            String oldString = etDisplay.getText().toString();
            int cursorPosition = etDisplay.getSelectionStart();
            String leftString = oldString.substring(0, cursorPosition);
            String rightString = oldString.substring(cursorPosition);
            etDisplay.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
            etDisplay.setSelection(cursorPosition + strToAdd.length());

            String newString = etDisplay.getText().toString();
            Expression exp = new Expression(newString);
            String finalResult = String.valueOf(exp.calculate());
            if (finalResult.endsWith(".0") || (finalResult.endsWith("NaN"))) {
                finalResult = finalResult.replace(".0", "");
                finalResult = finalResult.replace("NaN", "Invalid Format");
            }
            etSolution.setText(finalResult);


            isEqualsClicked = false;


        }

    }


    public void btnEquals(View view) {
        String answer = etSolution.getText().toString();
        etDisplay.setText(answer);
        etDisplay.setSelection(answer.length());
        etSolution.setText("");
        isEqualsClicked = true; //Flag
    }

    public void btnOne(View view) {
        updateDisplay("1");
    }

    public void btnTwo(View view) {
        updateDisplay("2");
    }

    public void btnThree(View view) {
        updateDisplay("3");
    }

    public void btnFour(View view) {
        updateDisplay("4");
    }

    public void btnFive(View view) {
        updateDisplay("5");
    }

    public void btnSix(View view) {
        updateDisplay("6");
    }

    public void btnSeven(View view) {
        updateDisplay("7");
    }

    public void btnEight(View view) {
        updateDisplay("8");
    }

    public void btnNine(View view) {
        updateDisplay("9");
    }

    public void btnZero(View view) {
        updateDisplay("0");
    }

    public void btnDot(View view) {
        updateDisplay(".");
    }

    public void btnRoot(View view) {
        updateDisplay("√");
    }

    public void btnPower(View view) {
        updateDisplay("^");
    }

    public void btnMod(View view) {
        updateDisplay("%");
    }

    public void btnPi(View view) {
        updateDisplay("π");
    }


    public void btnClear(View view) {
        etDisplay.setText("");
        etDisplay.setSelection(0);
        etSolution.setText("");
        isEqualsClicked = false;
        return;
    }

    public void btnBack(View view) {
        int cursorPosition = etDisplay.getSelectionStart();
        int strLength = etDisplay.getText().length();

        if (cursorPosition != 0 && strLength != 0) {
            SpannableStringBuilder goBack = (SpannableStringBuilder) etDisplay.getText();
            goBack.replace(cursorPosition - 1, cursorPosition, "");
            etDisplay.setText(goBack);
            //etSolution.setText(goBack);
            updateDisplay("");
            etDisplay.setSelection(cursorPosition - 1);
        }
    }

    public void btnAddition(View view) {
        updateDisplay("+");
    }

    public void btnSubtraction(View view) {
        updateDisplay("-");
    }

    public void btnDivision(View view) {
        updateDisplay("÷");
    }

    public void btnMultiplication(View view) {
        updateDisplay("×");
    }

    public void btnOpen(View view) {
        updateDisplay("(");
    }

    public void btnClose(View view) {
        updateDisplay(")");
    }

    public void cursorInBrackets(){
        int cursorPosition = etDisplay.getSelectionStart();
        etDisplay.setSelection(cursorPosition - 1);//Places the cursor between the brackets after the button is clicked.
    }

    public void btnNatlog(View view) {
        updateDisplay("ln()");
        cursorInBrackets();
    }

    public void btnLog(View view) {
        updateDisplay("lg()");
        cursorInBrackets();
    }

    public void btnSin(View view) {
        updateDisplay("sin()");
        cursorInBrackets();
    }

    public void btnCos(View view) {
        updateDisplay("cos()");
        cursorInBrackets();
    }

    public void btnTan(View view) {
        updateDisplay("tan()");
        cursorInBrackets();
    }

    public void btnFact(View view) {
        updateDisplay("!");

    }
}
