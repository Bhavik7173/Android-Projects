package com.gi.waitlist.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.waitlist.R;

import java.util.List;


public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    List<String> items;
    private Context context;

    public DemoAdapter(Context context, List<String> model) {
        this.context = context;
        this.items = model;
    }

    public DemoAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stu_year.setText(items.get(position));
        removeItem(position);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stu_name, stu_year;
        Button deleteBtn,editBtn;
        public ViewHolder(View itemView) {
            super(itemView);

            stu_name = (TextView) itemView.findViewById(R.id.et_student_name);
            stu_year = (TextView) itemView.findViewById(R.id.et_student_year);

            editBtn = (Button) itemView.findViewById(R.id.editBtn1);

        }
    }
}

//class DemoVH extends RecyclerView.ViewHolder {
//    TextView stu_name, stu_year;
//
//    private DemoAdapter da;
//
//    public DemoVH(@NonNull View itemView) {
//        super(itemView);
////        stu_name = itemView.findViewById(R.id.tv_guest_name);
//        stu_year = itemView.findViewById(R.id.tv_party_size);
//        itemView.findViewById(R.id.deleteBtn1).setOnClickListener(view -> {
//            da.items.remove(getAdapterPosition());
//            da.notifyItemRemoved(getAdapterPosition());
//        });
//    }
//
//    public DemoVH linkAdapter(DemoAdapter adapter) {
//        this.da = adapter;
//        return this;
//    }
//}
