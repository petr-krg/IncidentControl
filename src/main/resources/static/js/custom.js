/* custom javascript here */

function loadUserProfile() {
    fetch('/user/profile-info')
        .then(response => response.json())
        .then(data => {
            document.getElementById('lastName').value = data.userLastName;
            document.getElementById('firstName').value = data.userFirstName;
            document.getElementById('patName').value = data.userPatName;
            document.getElementById('userName').value = data.userDisplayName;
            document.getElementById('userPhone').value = data.userPhone;
            document.getElementById('birthdate').value = data.userBirthDate;
            document.getElementById('userLogin').value = data.userLogin;
            document.getElementById('userEmail').value = data.userEmail;

            fillControlSelected('userRole', data.allRoles, data.currentRole);
            fillControlSelected('userDepartment', data.allDepartment, data.currentDepartment)
            fillControlSelected('userPosition', data.allPosition, data.currentPosition)
            fillControlSelected('userDivision', data.allDivision, data.currentDivision)

        })
        .catch(error => console.error('Error:', error));
}

function fillControlSelected(controlName, listData, selectItem) {
    const select = document.getElementById(controlName);
    select.innerHTML = '';

  
    listData.forEach( data => {
        let option = document.createElement('option');
        option.value = data;
        option.text = data;
        option.selected = data === selectItem;
        select.appendChild(option);
    });  
}


function updateSelectOptions(selectId, options) {
    const select = document.getElementById(selectId);
    select.innerHTML = '';

    options.forEach(option => {
        let optionElement = document.createElement('option');
        optionElement.value = option;
        optionElement.text = option;
        select.appendChild(optionElement);
    });
}

function updateDivision(selectedIndex) {
    var selectedElement = document.getElementById('userDepartment');
    var departmentName = selectedElement.options[selectedIndex].text;
    var encodedDepartmentName = encodeURIComponent(departmentName);

    fetch(`/organizational-structure/get-division/${encodedDepartmentName}`)
        .then(response => response.json())
        .then(data => updateSelectOptions('userDivision', data))
        .catch(error => console.error('Error:', error));
}

function updatePosition(selectedIndex) {
    var selectedElement = document.getElementById('userDivision');
    var divisionName = selectedElement.options[selectedIndex].text;
    var encodedDivisionName = encodeURIComponent(divisionName);

    fetch(`/organizational-structure/get-positions/${encodedDivisionName}`)
        .then(response => response.json())
        .then(data => updateSelectOptions('userPosition', data))
        .catch(error => console.error('Error:', error));
}

function onDepartmentChange() {
    const departmentId = document.getElementById('departmentSelect').value;
    fetch(`/get-departments/${departmentId}`)
        .then(response => response.json())
        .then(departments => {
            const departmentSelect = document.getElementById('departmentSelect');
            departmentSelect.innerHTML = '';

            departments.forEach(department => {
                const option = document.createElement('option');
                option.value = department.id;
                option.text = department.name;
                departmentSelect.appendChild(option);
            });

            onDepartmentOrDivisionChange();
        })
        .catch(error => console.error('Error:', error));
}

function onDepartmentOrDivisionChange() {
    const officeId = document.getElementById('officeSelect').value;
    fetch(`/get-positions/${officeId}`)
        .then(response => response.json())
        .then(positions => {
        })
        .catch(error => console.error('Error:', error));
}