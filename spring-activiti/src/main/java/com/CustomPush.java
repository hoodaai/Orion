package com;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.beans.PropertyEditorSupport;

/**
 * Created by varun on 11/09/15.
 */
public class CustomPush implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Hello World !!!");
    }


}
