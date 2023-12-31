---
title: TextBoxActions
sidebar_position: 6
---

## Static methods

### `onTextBox (locator)` {#on-textbox-locator}

This will return `ITextBoxActions` which will expose different methods to handle all text box related actions.

```java
ITextBoxActions textBoxActions = TextBoxActions.onTextBox (locator);
```

## Instance methods

### `enterText (text)` {#enter-text}

This method is used to enter the given text to the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).enterText (text);
```

### `pressKey (key)` {#press-key}

This method is used to press the given key to the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
. . .
onTextBox (locator).pressKey (key);
```
