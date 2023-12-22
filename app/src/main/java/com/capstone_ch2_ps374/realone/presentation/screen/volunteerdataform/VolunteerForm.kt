package com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.capstone_ch2_ps374.realone.domain.usecase.MyDatePickerDialog
import com.capstone_ch2_ps374.realone.navigation.Screen
import com.capstone_ch2_ps374.realone.presentation.screen.login.UserData
import com.example.compose.md_theme_dark_error
import com.example.compose.md_theme_dark_onPrimary
import com.example.compose.md_theme_dark_primary
import com.maryamrzdh.stepper.Stepper


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerForm(
    viewModel: VolunteerFormViewModel = hiltViewModel(),
    userId: UserData?,
    navController: NavHostController
) {

    LaunchedEffect(key1 = Unit){
        viewModel.setUserId(userId!!.userId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

    LaunchedEffect(state.provinceList){
        viewModel.fetchProvince()
    }

    LaunchedEffect(key1 = state.userDataSucces){
        if (state.userDataSucces){
            navController.navigate(Screen.VolunteerLayout.route)
        }
    }

    LaunchedEffect(state.province){
        if (state.province.id != null){
            viewModel.fetchCity()
        }
    }

    val numberStep = 2

    val titleList= arrayListOf("Step 1","Step 2")

    if (state.isLoading) {
        Dialog(
            onDismissRequest = { /* Do nothing */ },
            content = {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        )
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Stepper(
            modifier = Modifier.fillMaxWidth(),
            numberOfSteps = numberStep,
            currentStep = state.currentStep,
            stepDescriptionList = titleList,
            selectedColor = md_theme_dark_onPrimary
        )
        if(state.currentStep == 1){
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(text = "Nama Lengkap")
                },
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(VolunteerFormEvent.NameChanged(it))
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
            var birthDate by remember {
                mutableStateOf("Tanggal lahir")
            }

            var showDatePicker by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(text = "Tanggal lahir")
                },
                value = state.birthDate,
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
                            showDatePicker = true
                        })
                },
            )
            if (state.birthDateError != null) {
                Text(
                    text = state.birthDateError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            if (showDatePicker) {
                MyDatePickerDialog(
                    onDateSelected = {
                        viewModel.onEvent(VolunteerFormEvent.BirthDateChanged(it))
                    },
                    onDismiss = { showDatePicker = false }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(text = "Pekerjaan")
                },
                value = state.job,
                onValueChange = {
                                viewModel.onEvent(VolunteerFormEvent.JobChanged(it))
                },
                isError = state.jobError != null,
                modifier = Modifier.fillMaxWidth(),
            )
            if (state.jobError != null) {
                Text(
                    text = state.jobError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            val options = listOf("SD", "SMP", "SMA", "S1/D4")

            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Pendidikan Terakhir")
                    },
                    readOnly = true,
                    value = state.highestEdu,
                    onValueChange = {
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
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
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(VolunteerFormEvent.HighestEduChanged(selectionOption))
                                expanded = false
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
            if (state.highestEduError != null) {
                Text(
                    text = state.highestEduError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            val optionsInterest: List<String> = listOf(
                "Dog training",
                "Pet grooming",
                "Animal shelter volunteering",
                "Mentoring",
                "Nursing",
                "Dog walking",
                "Veterinary medicine",
                "Counseling",
                "Tutoring",
                "Research",
                "Environmental conservation",
                "Wildlife conservation",
                "Digital marketing",
                "Gardening",
                "Teaching",
                "First aid",
                "Animal care",
                "Social media management",
                "Leadership development",
                "Childcare",
                "Event planning",
                "Data analysis",
                "Web development",
                "Social work",
                "Healthcare",
                "Youth mentoring",
                "Animal rescue",
                "Occupational therapy",
                "Leadership training",
                "Art therapy",
                "Nutrition",
                "Medical research",
                "Event coordination",
                "Environmental activism",
                "Medical coding",
                "Veterinary assistance",
                "Computer programming",
                "Public speaking",
                "Graphic design",
                "Environmental advocacy",
                "Photography",
                "Psychology",
                "Sports coaching",
                "Clinical research",
                "Physical therapy",
                "Health education",
                "Animal behavior modification",
                "Community outreach",
                "Medical assistance",
                "Pet adoption",
                "Animal surgery",
                "Mental health support",
                "Career guidance",
                "Environmental education",
                "Content creation",
                "Urban farming",
                "Computer literacy",
                "Customer service",
                "Pet adoption support",
                "Volunteer coordination",
                "Statistical modeling",
                "Database management",
                "Case management",
                "Communication",
                "Wildlife rehabilitation",
                "Geriatric care",
                "Rehabilitation",
                "Team building",
                "Special needs support",
                "English language tutoring",
                "Lab technician",
                "Volunteer management",
                "Conservation",
                "Emergency response",
                "Crisis intervention",
                "Administrative tasks",
                "Diet planning",
                "Animal behavior",
                "Advocacy",
                "Lab assistance",
                "Marketing",
                "Creative design",
                "Elderly care",
                "Youth empowerment",
                "Social media",
                "Mental health counseling",
                "Team management",
                "Visual communication",
                "Data management",
                "Fundraising"
            )

            var expandedInterest by remember { mutableStateOf(false) }
            var selectedOptionTextInterest by remember { mutableStateOf(optionsInterest[0]) }

            ExposedDropdownMenuBox(
                expanded = expandedInterest,
                onExpandedChange = {
                    expandedInterest = !expandedInterest
                }
            ) {
                OutlinedTextField(
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Ketertarikan")
                    },
                    readOnly = true,
                    value = state.interest,
                    onValueChange = {
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedInterest
                        )
                    },
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    placeholder = {
                        Text(text = "Ketertarikan")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedInterest,
                    onDismissRequest = { expandedInterest = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    optionsInterest.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(VolunteerFormEvent.InterestChanged(selectionOption))
                                expandedInterest = false
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
            if (state.interestError != null) {
                Text(
                    text = state.interestError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = state.phoneNumber,
                onValueChange = {
                                viewModel.onEvent(VolunteerFormEvent.PhoneNumberChanged(it))
                },
                isError = false,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "No telp")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            if (state.phoneNumberError != null) {
                Text(
                    text = state.phoneNumberError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.onEvent(VolunteerFormEvent.NextPage) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = md_theme_dark_primary
                    ),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "Selanjutnya")
                }
            }
        }else{
            Spacer(modifier = Modifier.height(30.dp))
            val optionsProvince = state.provinceList

            var expandedProvince by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expandedProvince,
                onExpandedChange = {
                    expandedProvince = !expandedProvince
                }
            ) {
                OutlinedTextField(
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Provinsi")
                    },
                    readOnly = true,
                    value = state.province.name?:"Provinsi",
                    onValueChange = {
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedProvince
                        )
                    },
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    placeholder = {
                        Text(text = "Ketertarikan")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedProvince,
                    onDismissRequest = { expandedProvince = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                   optionsProvince.forEachIndexed { index, selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(VolunteerFormEvent.ProvinceChanged(state.provinceList[index]))
                                expandedProvince = false
                            },
                            text = {
                                Text(
                                    text = selectionOption.name!!,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            if (state.interestError != null) {
                Text(
                    text = state.interestError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            val optionsCity = state.citiesList

            var expandedCity by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expandedCity,
                onExpandedChange = {
                    expandedCity = !expandedCity
                }
            ) {
                OutlinedTextField(
                    enabled = state.province.name != null,
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Kota")
                    },
                    readOnly = true,
                    value = state.city.name?:"Kota",
                    onValueChange = {
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expandedCity
                        )
                    },
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(

                        ),
                    placeholder = {
                        Text(text = "Ketertarikan")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedCity,
                    onDismissRequest = { expandedCity = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    optionsCity.forEachIndexed { index, selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(VolunteerFormEvent.CityChanged(state.citiesList[index]))
                                expandedCity = false
                            },
                            text = {
                                Text(
                                    text = selectionOption.name!!,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            if (state.cityError != null) {
                Text(
                    text = state.cityError!!,
                    color = md_theme_dark_error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.onEvent(VolunteerFormEvent.PrevPage) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = md_theme_dark_primary
                    ),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "Sebelumnya")
                }
                Button(
                    onClick = { viewModel.onEvent(VolunteerFormEvent.Submit) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = md_theme_dark_primary
                    ),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "Daftar")
                }
            }
        }
    }
}


@Preview
@Composable
fun VolunteerFormPreview() {
}