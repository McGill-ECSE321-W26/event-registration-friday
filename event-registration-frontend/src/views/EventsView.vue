<template>
  <div class="events-view">
    <h1>Events View</h1>
    <h2>All Events</h2>
    <table id="events-table">
      <tbody>
        <tr id="events-table-header">
          <th>Name</th>
          <th>Date</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Registration Limit</th>
        </tr>
        <tr v-for="event in events" class="events-table-row">
          <td>{{ event.name }}</td>
          <td>{{ event.date }}</td>
          <td>{{ event.startTime }}</td>
          <td>{{ event.endTime }}</td>
          <td>{{ event.registrationLimit }}</td>
        </tr>
      </tbody>
    </table>
    <h2>Create a New Event</h2>
    <input class="text-input" type="text" placeholder="Event Name" v-model="newEventName" />
    <input class="text-input" type="date" placeholder="Event Date" v-model="newEventDate" />
    <input class="text-input" type="time" placeholder="Start Time" v-model="newEventStartTime" />
    <input class="text-input" type="time" placeholder="End Time" v-model="newEventEndTime" />
    <input
      class="text-input"
      type="number"
      placeholder="Registration Limit"
      v-model="newEventRegistrationLimit"
    />
    <input type="radio" name="event-type" value="online" v-model="newEventType" /> Online
    <input type="radio" name="event-type" value="inperson" v-model="newEventType" /> In Person
    <input class="text-input" type="url" placeholder="url" v-if="newEventType === 'online'" />
    <input
      class="text-input"
      type="text"
      placeholder="address"
      v-if="newEventType === 'inperson'"
    />
    <button class="event-button" id="createBtn" @click="createEvent()">Create Event</button>
    <button class="event-button" id="clearBtn" @click="clear()">Clear</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

let events = [
  {
    id: 1,
    name: 'Event 1',
    date: '2024-07-01',
    startTime: '10:00',
    endTime: '12:00',
    registrationLimit: 100,
  },
  {
    id: 2,
    name: 'Event 2',
    date: '2024-07-02',
    startTime: '14:00',
    endTime: '16:00',
    registrationLimit: 50,
  },
  {
    id: 3,
    name: 'Event 3',
    date: '2024-07-03',
    startTime: '09:00',
    endTime: '11:00',
    registrationLimit: 200,
  },
  {
    id: 4,
    name: 'Event 4',
    date: '2024-07-04',
    startTime: '13:00',
    endTime: '15:00',
    registrationLimit: 150,
  },
]

let newEventName = ref(null)
let newEventDate = ref(null)
let newEventStartTime = ref(null)
let newEventEndTime = ref(null)
let newEventRegistrationLimit = ref(null)
let newEventType = ref('online')

function createEvent() {
  const newEvent = {
    id: events.length + 1,
    name: newEventName.value,
    date: newEventDate.value,
    startTime: newEventStartTime.value,
    endTime: newEventEndTime.value,
    registrationLimit: newEventRegistrationLimit.value,
  }

  events.push(newEvent)

  newEventName.value = null
  newEventDate.value = null
  newEventStartTime.value = null
  newEventEndTime.value = null
  newEventRegistrationLimit.value = null
  newEventType.value = 'online'
}

function clear() {
  newEventName.value = null
  newEventDate.value = null
  newEventStartTime.value = null
  newEventEndTime.value = null
  newEventRegistrationLimit.value = null
  newEventType.value = 'online'
}
</script>

<style scoped>
#events-table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #000;
}
#events-table-header {
  background-color: #f2f2f2;
}
#events-table-header th {
  border: 1px solid #000;
  padding: 8px;
  text-align: left;
}
.events-table-row td {
  border: 1px solid #ccc;
  padding: 8px;
}
.event-button {
  color: #fff;
  border: 1px solid #fff;
  border-radius: 4px;
}

#createBtn {
  background-color: green;
}

#clearBtn {
  background-color: red;
}

.text-input {
  border: 1px solid #ccc;
  border-radius: 4px;
  margin: 8px;
}
</style>