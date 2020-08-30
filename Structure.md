https://proandroiddev.com/a-guide-to-recyclerview-selection-3ed9f2381504

https://habr.com/ru/post/258195/

1
 in Gradle
implementation 'androidx.recyclerview:recyclerview-selection:1.0.0'

2
Select a key type
    - Parcelable
    - String
    - Long
        Если использовать то нужны статичные ID

        init {
            setHasStableIds(true)
        }

        В адаптере
        override fun getItemId(position: Int): Long = position.toLong()

3
 Implement (or not) KeyProvider
    StableIdKeyProvider
    Не понятно что куда

4
Implement ItemDetailsLookup

    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as MainAdapter.ViewHolder)
                .getItemDetails()
        }
        return null
    }
}

5
 new method in our ViewHolder

 fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
     object : ItemDetailsLookup.ItemDetails<Long>() {
         override fun getPosition(): Int = adapterPosition
         override fun getSelectionKey(): Long? = itemId
     }

6
Highlighting the selected items

     <?xml version="1.0" encoding="utf-8"?>
     <selector xmlns:android="http://schemas.android.com/apk/res/android">
         <item android:drawable="@android:color/holo_blue_light" android:state_activated="true" />
         <item android:drawable="@android:color/white" />
     </selector>

 Then adding the drawable as the background colour in our item layout

 android:background="@drawable/item_background"


 7
 create tracker in MainActivity

 var tracker: SelectionTracker<Long>? = null

 TODO read and add bind method in adapter
 new boolean parameter to the bind method

 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val number = list[position]
     tracker?.let {
         holder.bind(number, it.isSelected(position.toLong()))
     }
 }

 8
 change the bind method to use the new boolean to activate or not the view.

 fun bind(value: Int, isActivated: Boolean = false) {
     text.text = value.toString()
     itemView.isActivated = isActivated
 }

 9
 Create a tracker

  to our MainActivity and create a new tracker. To do that we will use the SelectionTracker.Builder

  selectionId: a string to identity our selection in the context of the activity or fragment.
  recyclerView: the RecyclerView where we will apply the tracker.
  keyProvider: the source of selection keys.
  detailsLookup: the source of information about RecyclerView items.
  storage: strategy for type-safe storage of the selection state.



10
  will allow multiple items to be selected without any restriction.

  tracker = SelectionTracker.Builder<Long>(
      "mySelection",
      recyclerView,
      StableIdKeyProvider(recyclerView),
      MyItemDetailsLookup(recyclerView),
      StorageStrategy.createLongStorage()
  ).withSelectionPredicate(
      SelectionPredicates.createSelectAnything()
  ).build()
  adapter.tracker = tracker