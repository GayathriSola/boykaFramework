---
title: IDriverActionsListener
sidebar_position: 4
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling driver specific actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onExecuteScript (script, args)` {#on-execute-script}

This method will get executed after executing the script on the driver using provided args.

## `onPause (time)` {#on-pause}

This method will get executed after pausing for the mentioned duration.

## `onSaveLogs` {#on-save-logs}

This method will get executed after saving all the driver logs.

## `onWaitUntil` {#on-wait-until}

This method will get executed after waiting until a particular condition.
