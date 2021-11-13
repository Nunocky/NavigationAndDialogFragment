# NavigationAndDialogFragment
Android ナビゲーショングラフに Dialogを追加する

## 解説
### ナビゲーショングラフ
```
    <fragment
        android:id="@+id/mainFragment"
        android:name="org.nunocky.navigationanddialogfragment.MainFragment"
        android:label="MainFragment">
        <action
            android:id="@+id/action_mainFragment_to_yesNoDialogFragment"
            app:destination="@id/yesNoDialogFragment" />
    </fragment>
    <dialog
        android:id="@+id/yesNoDialogFragment"
        android:name="org.nunocky.navigationanddialogfragment.YesNoDialogFragment"
        android:label="YesNoDialogFragment" />
```


### フラグメントからダイアログを開く

```
    private fun showDialog() {
        findNavController().navigate(R.id.yesNoDialogFragment)
    }
```

### ダイアログの Yes/Noの結果

まず返り値のクラスを作る

```
    sealed class Result {
        object Init : Result()
        object Yes : Result()
        object No : Result()
    }
```

この返り値を Fragmentと Dialogで共有する。そのために Activityスコープの ViewModelを使う。

```
class DialogResultViewModel : ViewModel() {
    val result = MutableLiveData<YesNoDialogFragment.Result>(YesNoDialogFragment.Result.Init)
}
```

```
    private val resultViewModel: DialogResultViewModel by activityViewModels()
```

Fragmentは viewmodel.resultを observeし、ボタン押下時の挙動を実装する。

```
        resultViewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                YesNoDialogFragment.Result.Yes -> {
                    Toast.makeText(requireActivity(), "YES", Toast.LENGTH_SHORT).show()
                }
                YesNoDialogFragment.Result.No -> {
                    Toast.makeText(requireActivity(), "NO", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }
```

## 感想

個人的にはこの実装方法は好きではない。 一番の理由は YesNoDialogを使い回すことができないから。 Android Navigationは、 この例のように汎用的に用いられるダイアログが再利用されることを考慮していない。 

再利用性の観点で言えば従来のコールバックを用いる方法のほうがコードも読みやすい。

