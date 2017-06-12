package fokia.gq.zhifu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by archie on 6/12/17.
 */

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    private List<Income> mIncomes;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView incomeMoney;
        TextView incomeNote;
        TextView incomeDate;
        public ViewHolder(View view){
            super(view);
            incomeMoney = (TextView) view.findViewById(R.id.payment_money);
            incomeNote = (TextView) view.findViewById(R.id.payment_note);
            incomeDate = (TextView) view.findViewById(R.id.payment_date);
        }
    }
    public IncomeAdapter(List<Income> incomes){
        mIncomes = incomes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.constrain_payment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Income income = mIncomes.get(position);
        holder.incomeMoney.setText(income.getMoney()+"");
        holder.incomeNote.setText(income.getNote());
        holder.incomeDate.setText(income.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mIncomes.size();
    }
}
