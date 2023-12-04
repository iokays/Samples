package com.iokays.analysispattern.accountabilityknowledgelevel;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * 关联规则
 *
 */
public class ConnectionRule {

    final PartyType allowedParent;
    final PartyType allowedChild;

    public ConnectionRule(PartyType allowedParent, PartyType allowedChild) {
        this.allowedParent = allowedParent;
        this.allowedChild = allowedChild;
    }

    public boolean isVaild(Party parent, Party child) {
        return Objects.equals(parent.type(), allowedParent) && Objects.equals(child.type(), allowedChild);
    }

}
