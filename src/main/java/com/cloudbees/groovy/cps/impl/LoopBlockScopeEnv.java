package com.cloudbees.groovy.cps.impl;

import com.cloudbees.groovy.cps.Continuation;
import com.cloudbees.groovy.cps.Env;

/**
 * Block scope for a loop, which affects the target of break/continue.
 *
 * @author Kohsuke Kawaguchi
 */
class LoopBlockScopeEnv extends BlockScopeEnv {
    private final String label;
    private final Continuation break_, continue_;

    LoopBlockScopeEnv(Env parent, String label, Continuation break_, Continuation continue_) {
        super(parent);
        this.label = label;
        this.break_ = break_;
        this.continue_ = continue_;
    }

    @Override
    public Continuation getBreakAddress(String label) {
        if (labelMatch(label))  return break_;
        else                    return null;
    }

    @Override
    public Continuation getContinueAddress(String label) {
        if (labelMatch(label))  return continue_;
        else                    return null;
    }

    private boolean labelMatch(String given) {
        return given==null || given.equals(label);
    }
}
