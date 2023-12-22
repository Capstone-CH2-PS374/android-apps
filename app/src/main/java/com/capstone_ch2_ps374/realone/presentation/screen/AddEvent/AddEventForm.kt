package com.capstone_ch2_ps374.realone.presentation.screen.AddEvent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capstone_ch2_ps374.realone.api.CategoryResponse
import com.capstone_ch2_ps374.realone.domain.usecase.MyDatePickerDialog
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform.VolunteerFormEvent
import com.example.compose.md_theme_dark_error
import com.example.compose.md_theme_dark_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventForm(
    viewModel: AddFormViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCategory()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Kegiatan Baru:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall,
        )
        //sub judul
        Text(
            text = "Temukan Relawan",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(text = "Nama Kegiatan")
            },
            value = state.name,
            onValueChange = {
                viewModel.onEvent(AddEventEvent.NameChanged(it))
            },
            isError = state.nameError != null,
            modifier = Modifier.fillMaxWidth(),
        )
        if (state.nameError != null) {
            Text(
                text = state.nameError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        var startDate by remember {
            mutableStateOf("Waktu Mulai")
        }

        var showStartDatePicker by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(text = "Waktu Mulai")
            },
            value = state.startTime,
            onValueChange = {

            },
            readOnly = true,
            isError = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showStartDatePicker = true
                    })
            },
        )
        if (state.startTimeError != null) {
            Text(
                text = state.startTimeError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        if (showStartDatePicker) {
            MyDatePickerDialog(
                onDateSelected = {
                    viewModel.onEvent(AddEventEvent.StartChanged(it))
                },
                onDismiss = { showStartDatePicker = false },
                isFuture = true
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        var endDate by remember {
            mutableStateOf("Waktu Berakhir")
        }

        var showEndDatePicker by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(text = "Waktu Berakhir")
            },
            value = state.endTime,
            onValueChange = {

            },
            readOnly = true,
            isError = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showEndDatePicker = true
                    })
            },
        )
        if (state.endTimeError != null) {
            Text(
                text = state.endTimeError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        if (showEndDatePicker) {
            MyDatePickerDialog(
                onDateSelected = {
                    viewModel.onEvent(AddEventEvent.EndChanged(it))
                },
                onDismiss = { showEndDatePicker = false },
                isFuture = true
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val optionsType = listOf("Online", "Offline")

        var expandedType by remember { mutableStateOf(false) }
        var selectedOptionType by remember { mutableStateOf(optionsType[0]) }

        ExposedDropdownMenuBox(
            expanded = expandedType,
            onExpandedChange = {
                expandedType = !expandedType
            }
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(text = "Tipe Kegiatan")
                },
                readOnly = true,
                value = state.type,
                onValueChange = {
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedType
                    )
                },
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedType,
                onDismissRequest = { expandedType = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                optionsType.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEventEvent.TypeChanged(selectionOption))
                            expandedType = false
                        },
                        text = {
                            Text(
                                text = selectionOption,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        if (state.typeError != null) {
            Text(
                text = state.typeError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(text = "Deskripsi Kegiatan")
            },
            value = state.desc,
            onValueChange = {
                viewModel.onEvent(AddEventEvent.NameChanged(it))
            },
            isError = state.descError != null,
            modifier = Modifier.fillMaxWidth(),
        )
        if (state.descError != null) {
            Text(
                text = state.descError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val optionsField = state.categories

        var expandedField by remember { mutableStateOf(false) }
        var selectedOptionField by remember { mutableStateOf(optionsField?.get(0) ?: CategoryResponse()) }

        ExposedDropdownMenuBox(
            expanded = expandedField,
            onExpandedChange = {
                expandedField = !expandedField
            }
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(text = "Kategori Kegiatan")
                },
                readOnly = true,
                value = state.field.category?:"Kategori Kegiatan",
                onValueChange = {
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedField
                    )
                },
                isError = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedField,
                onDismissRequest = { expandedField = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                optionsField?.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddEventEvent.FieldChanged(selectionOption))
                            expandedField = false
                        },
                        text = {
                            selectionOption.category?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        if (state.fieldError != null) {
            Text(
                text = state.fieldError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        var enrollDate by remember {
            mutableStateOf("Waktu Berakhir")
        }

        var showEnrollDatePicker by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(text = "Batas Daftar")
            },
            value = state.enrollDeadline,
            onValueChange = {

            },
            readOnly = true,
            isError = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showEnrollDatePicker = true
                    })
            },
        )
        if (state.enrollDeadlineError != null) {
            Text(
                text = state.enrollDeadlineError!!,
                color = md_theme_dark_error,
                modifier = Modifier.align(Alignment.End)
            )
        }

        if (showEnrollDatePicker) {
            MyDatePickerDialog(
                onDateSelected = {
                    viewModel.onEvent(AddEventEvent.EnrollDeadlineChanged(it))
                },
                onDismiss = { showEnrollDatePicker = false },
                isFuture = true
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { viewModel.onEvent(AddEventEvent.Submit) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_dark_primary
                ),
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Buat Event")
            }
        }
    }
}