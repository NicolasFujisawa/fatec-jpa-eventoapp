import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
});

export default {
  async getEventsByUserName(user) {
    const auth = this.genAuth(user);

    return axiosInstance.get(`/events?username=${user.name}`, { auth });
  },
  genAuth(user) {
    return { username: user.name, password: user.password };
  },
};
