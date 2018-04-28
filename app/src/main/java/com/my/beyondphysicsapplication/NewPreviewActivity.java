package com.my.beyondphysicsapplication;

import com.beyondphysics.ui.imagechooselibrary.PreviewActivity;
import com.beyondphysics.ui.utils.BeyondPhysicsManagerParams;


public class NewPreviewActivity extends PreviewActivity {
    @Override
    public BeyondPhysicsManagerParams getBeyondPhysicsManagerParams() {
        return NewBaseActivity.getBeyondPhysicsManagerParams(this);
    }
}
