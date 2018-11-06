package com.example.luqni.databarang;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BarangAdapter  extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    List <Barang> barangs;

    public BarangAdapter(List <Barang> barangs) {
        this.barangs = barangs;
    }

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.row_barang, viewGroup, false );
        BarangViewHolder barangViewHolder = new BarangViewHolder( v );
        return barangViewHolder;
    }

    @Override
    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {
        barangViewHolder.barangName.setText( barangs.get( i ).getNama() );
        barangViewHolder.barangHarga.setText( barangs.get( i ).getHarga() );
        barangViewHolder.barangKode.setText( barangs.get( i ).getKode() );
    }

    @Override
    public int getItemCount() {
        return barangs.size();
    }

    public Barang getItem(int position) {
        return barangs.get( position );
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView( recyclerView );
    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView barangName;
        TextView barangHarga;
        TextView barangKode;

        BarangViewHolder(View itemView) {
            super( itemView );
            cv = (CardView) itemView.findViewById( R.id.cv );
            barangName = (TextView) itemView.findViewById( R.id.textViewRowNama );
            barangHarga = (TextView) itemView.findViewById( R.id.textViewRowHarga );
            barangKode = (TextView) itemView.findViewById( R.id.textViewRowKode );
        }
    }
}
