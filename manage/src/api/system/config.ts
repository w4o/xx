import request from '/@/utils/request';

export function useConfigApi() {
	return {
		findPage: (params?: object) => {
			return request({
				url: '/sys/config',
				method: 'get',
				params,
			});
		},
		add: (data: object) => {
			return request({
				url: '/sys/config',
				method: 'post',
				data,
			});
		},
		update: (id: string, data: object) => {
			return request({
				url: `/sys/config/${id}`,
				method: 'put',
				data,
			});
		},
	};
}
