# MonthPickerDialog
A Month Picker for those who need to take a month from a year without having to pick a date.

# Dependencies
[HorizontalPicker](https://github.com/blazsolar/HorizontalPicker)

# Usage

```java
    /* instantiate your month picker
    ------------------------------------------------------------------------ */
    MonthPickerDialog picker = new MonthPickerDialog(context);
    
    /* you can perform an action when you're showing your dialog (using DialogInterface.OnShowListener)
    ------------------------------------------------------------------------ */
    picker.setOnShowListener(listener);
    
    /* you can perform an action whenever you pick your month (using MonthPickerDialog.OnMonthPickListener)
    ------------------------------------------------------------------------ */
    picker.setOnMonthPickListener(listener);
    
    /* show your month picker
    ------------------------------------------------------------------------ */
    picker.show();
    
    /* hide your month picker
    ------------------------------------------------------------------------ */
    picker.hide();
```
