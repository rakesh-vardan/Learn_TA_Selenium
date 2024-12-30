# Xpath

## Basic XPath Locators:

1. Text Input Field (with name="my-text")
```
//input[@name='my-text']
```

2. Disabled Input Field
```
//input[@name='my-disabled' and @disabled]
```

3. Option in Dropdown (with value '1')
```
//select[@name='my-select']/option[@value='1']
```

4. File Input Field
```
//input[@type='file' and @name='my-file']
```

5. Checkbox (Checked Checkbox)
```
//input[@type='checkbox' and @name='my-check' and @checked]
```

6. Find the Submit Button Using class Attribute
```
//button[@class='btn btn-outline-primary mt-3']
```

## XPath Locators Using Functions:

1. Find the 'Text input' Label Using normalize-space to Ignore Leading/Trailing Spaces
```
//label[normalize-space(text())='Text input']
```

2. Find Input Elements That Have 'form-control' in Their class Attribute
```
//input[contains(@class, 'form-control')]
```

3. Find the Last Option in the 'my-select' Dropdown
```
//select[@name='my-select']/option[last()]
```

## XPath Axes:

1. Find the Label that is parent to the Input Field
```
//input[@name='my-text']/parent::label
```

2. Find all the ancestor divs to the Input Field
```
//input[@name='my-text']/ancestor::div
```

3. Find First option for the Select element options
```
//select[@name='my-select']/option[@value='1']/preceding-sibling::option
```

# CSS

1. Text Input Field
```
input[name='my-text']
```

2. Disabled Input Field
```
input[name='my-disabled'][disabled]
```

3. Option in Dropdown (with value='1')
```
select[name='my-select'] option[value='1']
```

4. File Input Field
```
input[type='file'][name='my-file']
```

5. Checked Checkbox
```
button.btn.btn-outline-primary.mt-3
```

6. Last Option in Select Dropdown
```
select[name='my-select'] option:last-child
```