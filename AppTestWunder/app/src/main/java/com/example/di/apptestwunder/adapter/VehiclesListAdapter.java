package com.example.di.apptestwunder.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.di.apptestwunder.R;
import com.example.di.apptestwunder.model.Vehicles;
import com.example.di.apptestwunder.view.MapsFragmentActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class VehiclesListAdapter extends RecyclerView.Adapter<VehiclesListAdapter.ViewHolder>  implements Filterable {

    private ArrayList<Vehicles> itemList, filterList;
    private Context context;

    public VehiclesListAdapter(Context context, ArrayList<Vehicles> itemList) {
        this.itemList = itemList;
        this.filterList = itemList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : filterList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_marker, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Vehicles veh = filterList.get(position);

        holder.txtName.setText(veh.getName());
        holder.txtInteriorExterior.setText("Interior: "+ String.valueOf(veh.getInterior())
                                          + " | " +"Exterior: "+ String.valueOf(veh.getExterior()));
        holder.vin_fue_type.setText("Vin: "+ veh.getVin()
                                    + " | "  + "Fuel: "+ veh.getFuel()
                                    + " | "  + "Type: "+ veh.getEngineType());
        holder.address.setText("Address: "+ veh.getAddress());



        holder.rowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent a = new Intent(context, MapsFragmentActivity.class);
                ArrayList latLngs = new ArrayList<LatLng>();
                ArrayList address = new ArrayList<String>();
                ArrayList names = new ArrayList<String>();


                for( Vehicles veh : itemList) {
                    latLngs.add(new LatLng(veh.getCoordinates().get(1).doubleValue(), veh.getCoordinates().get(0).doubleValue()));
                    address.add(veh.getAddress());
                    names.add(veh.getName());
                }

                a.putParcelableArrayListExtra("LAT_LONG", latLngs);
                a.putParcelableArrayListExtra("ADDRESS", (ArrayList<? extends Parcelable>) address);
                a.putParcelableArrayListExtra("NAMES", (ArrayList<? extends Parcelable>) names);
                a.putExtra("POSITION", veh.getName ());

                context.startActivity(a);
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder  {
        LinearLayout rowId;
        TextView txtName;
        TextView txtInteriorExterior;
        TextView vin_fue_type;
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            rowId =  itemView.findViewById(R.id.rowId);
            txtName =  itemView.findViewById(R.id.txt_name);
            txtInteriorExterior =  itemView.findViewById(R.id.txt_interior_exterior);
            vin_fue_type = itemView.findViewById(R.id.txt_vin_fue_type);
            address = itemView.findViewById(R.id.txt_address);
        }
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    filterList = itemList;
                } else {
                    ArrayList<Vehicles> filteredList = new ArrayList<>();

                    for (Vehicles list : itemList) {
                        if (list.getName().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(list);
                        }
                    }
                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (ArrayList<Vehicles>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
