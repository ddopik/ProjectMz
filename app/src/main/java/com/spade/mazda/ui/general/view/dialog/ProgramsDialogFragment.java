package com.spade.mazda.ui.general.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.spade.mazda.R;
import com.spade.mazda.ui.general.view.adapters.ProgramsAdapter;
import com.spade.mazda.ui.general.view.interfaces.ProgramsInterface;
import com.spade.mazda.ui.services.model.Program;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/19/17.
 */

public class ProgramsDialogFragment extends DialogFragment implements ProgramsInterface {

    private ProgramsInterface programsInterface;
    private List<Program> programs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_listing, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView modelsRecyclerView = view.findViewById(R.id.items_recycler_view);
        ProgramsAdapter programsAdapter = new ProgramsAdapter(programs, getContext());
        programsAdapter.setProgramsActions(this);
        modelsRecyclerView.setAdapter(programsAdapter);
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public void setProgramsInterface(ProgramsInterface programsInterface) {
        this.programsInterface = programsInterface;
    }

    @Override
    public void onProgramSelected(Program program) {
        programsInterface.onProgramSelected(program);
        dismiss();
    }
}
